package com.proiect.licenta.dto;


import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecDTO {

    private int recId;
    private String placeName;
    private String address;
    private String initialComment;
    private String category;

    public RecDTO(Recommendation recommendation){
        this.recId = recommendation.getRecId();
        this.placeName = recommendation.getPlaceName();
        this.address = recommendation.getAddress();
        this.initialComment = recommendation.getInitialComment();
        this.category = recommendation.getCategory();
    }


}
