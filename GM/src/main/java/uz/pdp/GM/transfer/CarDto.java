package uz.pdp.GM.transfer;

import lombok.Data;

import javax.persistence.Column;
@Data
public class CarDto {

    private String model;

    private Integer year;

    private Integer price;

    private Integer autoshopId;

}
