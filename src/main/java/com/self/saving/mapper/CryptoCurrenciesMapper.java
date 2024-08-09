package com.self.saving.mapper;

import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import com.self.saving.model.CryptoCurrenciesModel;
import com.self.saving.model.CryptoProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CryptoCurrenciesMapper {

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "ema.productId", source = "id")
    CryptoCurrenciesInfoEntity toCryptoCurrenciesInfoEntity(CryptoCurrenciesModel model);


    List<CryptoCurrenciesInfoEntity> toCryptoCurrenciesInfoEntities(List<CryptoCurrenciesModel> models);


    List<CryptoCurrenciesModel> toCryptoCurrenciesModels(List<CryptoCurrenciesInfoEntity> entities);

    @Mapping(target = "id", source = "productId")
    CryptoCurrenciesModel toCryptoCurrenciesModel(CryptoCurrenciesInfoEntity entity);


}
