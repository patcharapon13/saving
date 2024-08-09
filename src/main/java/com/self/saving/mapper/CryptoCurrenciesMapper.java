package com.self.saving.mapper;

import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import com.self.saving.entity.EmaEntity;
import com.self.saving.model.CryptoCurrenciesModel;
import com.self.saving.model.CryptoProductModel;
import com.self.saving.model.EmaModel;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CryptoCurrenciesMapper {

    @Mapping(target = "productId", source = "id")
    CryptoCurrenciesInfoEntity toCryptoCurrenciesInfoEntity(CryptoCurrenciesModel model);

    List<CryptoCurrenciesInfoEntity> toCryptoCurrenciesInfoEntities(List<CryptoCurrenciesModel> models);


    @Mapping(target = "productId", source = "id")
    @Mapping(target = "price", source = "priceStatus.price")
    @Mapping(target = "ema.productId", source = "id")
    CryptoCurrenciesInfoEntity toCryptoCurrenciesInfoEntity(CryptoProductModel models);

    Set<CryptoCurrenciesInfoEntity> toCryptoCurrenciesInfoEntities(Set<CryptoProductModel> models);

    List<CryptoCurrenciesModel> toCryptoCurrenciesModels(List<CryptoCurrenciesInfoEntity> entities);

    @Mapping(target = "id", source = "productId")
    CryptoCurrenciesModel toCryptoCurrenciesModel(CryptoCurrenciesInfoEntity entity);


}
