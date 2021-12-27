package uz.pdp.GM.transfer;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GMDto {

    private String corpName;
    private String directorName;
    private String street;
    private Integer number;

}
