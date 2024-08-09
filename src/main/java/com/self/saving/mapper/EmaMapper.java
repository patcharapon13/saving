package com.self.saving.mapper;

import com.self.saving.entity.EmaEntity;
import com.self.saving.model.EmaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmaMapper {

    @Named("toEma")
    EmaEntity toEmaEntity(EmaModel emaModel);
    List<EmaEntity> toEmaEntities(List<EmaModel> emaModels);

}
