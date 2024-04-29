package com.h87.sondji.domain.note;

import com.h87.sondji.domain.commons.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_note")
public class Note extends EntityBase {
    private NoteTit
}
