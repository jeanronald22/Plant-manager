package com.dev.plant_management.service.implementations;


import com.dev.plant_management.entity.Plant;
import com.dev.plant_management.entity.WateringHistory;
import com.dev.plant_management.payload.request.WateringHistoryRequest;
import com.dev.plant_management.payload.response.WateringHistoryResponse;
import com.dev.plant_management.payload.response.WateringNeedResponse;
import com.dev.plant_management.repository.WateringHistoryRepository;
import com.dev.plant_management.service.WateringHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WateringHistoryServiceImpl implements WateringHistoryService {

    private final WateringHistoryRepository wateringHistoryRepository;
    private final ModelMapper mapper;

    @Override
    public WateringHistoryResponse saveHistory(WateringHistoryRequest request, Plant plant) {
        WateringHistory history = mapper.map(request, WateringHistory.class);
        history.setPlant(plant);
        return mapper.map(wateringHistoryRepository.save(history), WateringHistoryResponse.class);
    }

    @Override
    public Optional<WateringHistoryResponse> findById(Long id) {
        return Optional.ofNullable(mapper.map(wateringHistoryRepository.findById(id), WateringHistoryResponse.class));
    }

    @Override
    public List<WateringHistoryResponse> findByPlant(Plant plant) {
        return wateringHistoryRepository.findByPlant(plant).stream().map(it ->
                mapper.map(it, WateringHistoryResponse.class)).toList();
    }

    @Override
    public List<WateringHistoryResponse> findAll() {
        return wateringHistoryRepository.findAll().stream().map(it ->
                mapper.map(it, WateringHistoryResponse.class)).toList();
    }

    @Override
    public void delete(Long id) {
        wateringHistoryRepository.deleteById(id);
    }
}
