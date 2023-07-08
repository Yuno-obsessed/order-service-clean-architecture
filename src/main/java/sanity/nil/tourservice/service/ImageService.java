package sanity.nil.tourservice.service;

import sanity.nil.tourservice.infrastructure.database.model.Image;

import java.util.UUID;

public interface ImageService extends BaseService<Image, UUID> {

    void getFile(UUID id);
}
