package uz.pdp.GM.transfer;

import lombok.Data;
import uz.pdp.GM.entity.Address;
import uz.pdp.GM.entity.GM;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class AutoShopDto {


    private String name;
    private String street;
    private Integer number;
    private Integer gmId;

}

