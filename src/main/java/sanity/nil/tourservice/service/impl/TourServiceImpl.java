package sanity.nil.tourservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanity.nil.tourservice.dao.TourRepository;
import sanity.nil.tourservice.entity.Tour;
import sanity.nil.tourservice.service.TourService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Override
    public void save(Tour entity) {
        tourRepository.save(entity);
    }

    @Override
    public Tour get(UUID id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @Override
    public void update(Tour entity) {
        tourRepository.findById(entity.getTourId()).ifPresent(updatedTour -> tourRepository.save(entity));
    }

    @Override
    public boolean delete(UUID id) {
        tourRepository.deleteById(id);
        return !tourRepository.existsById(id);
    }
}
