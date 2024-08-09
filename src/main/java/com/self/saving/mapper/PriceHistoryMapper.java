package com.self.saving.mapper;

import com.self.saving.model.PriceHistories;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PriceHistoryMapper {
    default PriceHistories toPriceHistories(Object[] array) {
        return PriceHistories.builder()
                .bucketTime(toLocalDateTime((Integer) array[0]))
                .closingPrice(toBigDecimal(array[4])).build();
    }

    default LocalDateTime toLocalDateTime(Integer epochSeconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds), ZoneId.systemDefault());
    }

    default BigDecimal toBigDecimal(Object value) {
        return new BigDecimal(value.toString());
    }

    default List<PriceHistories> toPriceHistoriesList(Object[][] arrays) {
        return Arrays.stream(arrays)
                .map(this::toPriceHistories)
                .toList();
    }
}
