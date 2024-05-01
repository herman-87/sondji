package com.h87.sondji.domain.tag;


import com.h87.sondji.commons.EntityBase;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_tag")
public class Tag extends EntityBase {
    @Embedded
    private TagName name;
    @Embedded
    private TagDescription description;
}
