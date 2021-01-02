package com.agrobalkan.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class RatingDto {

    private Long productId;

    private float stars;
}
