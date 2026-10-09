package com.guild.lootandlogistics.apirest.controller;

import com.guild.lootandlogistics.apirest.api.QuestsApi;
import com.guild.lootandlogistics.apirest.dto.CreateDeliveryQuestRequestDTO;
import com.guild.lootandlogistics.apirest.dto.DeliveryQuestResponseDTO;
import com.guild.lootandlogistics.apirest.mapper.DeliveryQuestMapper;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;
import com.guild.lootandlogistics.domain.usecase.FindAllDeliveryQuestsUseCase;
import com.guild.lootandlogistics.domain.usecase.GetDeliveryQuestUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Endpoints de los encargos del gremio
 * Implementa la interfaz generada desde el contrato OpenAPI
 */
@RestController
public class DeliveryQuestController implements QuestsApi {

    // Solo usa los puertos del dominio, no los servicios
    private final CreateDeliveryQuestUseCase createDeliveryQuestUseCase;
    private final GetDeliveryQuestUseCase getDeliveryQuestUseCase;
    private final DeliveryQuestMapper mapper;
    private final FindAllDeliveryQuestsUseCase findAllDeliveryQuestsUseCase;

    public DeliveryQuestController(CreateDeliveryQuestUseCase createDeliveryQuestUseCase,
                                   GetDeliveryQuestUseCase getDeliveryQuestUseCase,
                                   FindAllDeliveryQuestsUseCase findAllDeliveryQuestsUseCase,
                                   DeliveryQuestMapper mapper) {
        this.createDeliveryQuestUseCase = createDeliveryQuestUseCase;
        this.getDeliveryQuestUseCase = getDeliveryQuestUseCase;
        this.findAllDeliveryQuestsUseCase = findAllDeliveryQuestsUseCase;
        this.mapper = mapper;
    }

    // POST /quests - crear encargo
    @Override
    public ResponseEntity<DeliveryQuestResponseDTO> createDeliveryQuest(CreateDeliveryQuestRequestDTO request) {
        // Si los datos no son válidos, el dominio lanza la excepción y el handler responde 400
        DeliveryQuest quest = createDeliveryQuestUseCase.create(
                request.getTitle(),
                mapper.toDomain(request.getOrigin()),
                mapper.toDomain(request.getDestination()),
                mapper.toDomain(request.getCargo()),
                mapper.toDomain(request.getReward()),
                mapper.toDomain(request.getDangerLevel()));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(quest));
    }

    // GET /quests/{id} - consultar encargo
    @Override
    public ResponseEntity<DeliveryQuestResponseDTO> getDeliveryQuest(UUID id) {
        DeliveryQuest quest = getDeliveryQuestUseCase.get(new QuestId(id));
        return ResponseEntity.ok(mapper.toResponse(quest));
    }

    // GET /quests - listar encargos
    @Override
    public ResponseEntity<List<DeliveryQuestResponseDTO>> findAllDeliveryQuests() {
        // Sin encargos devuelve 200 con lista vacía, no 404
        List<DeliveryQuest> quests = findAllDeliveryQuestsUseCase.findAll();

        return ResponseEntity.ok(mapper.toResponse(quests));
    }
}