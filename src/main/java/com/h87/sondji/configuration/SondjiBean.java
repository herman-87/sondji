package com.h87.sondji.configuration;

import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.repository.NoteSpringRepository;
import com.h87.sondji.repository.TagSpringRepository;
import com.h87.sondji.service.NoteDefaultRepository;
import com.h87.sondji.service.TagDefaultRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SondjiBean {

    @Bean
    NoteRepository noteRepository(NoteSpringRepository noteSpringRepository) {
        return new NoteDefaultRepository(noteSpringRepository);
    }

    @Bean
    TagRepository tagRepository(TagSpringRepository tagSpringRepository) {
        return new TagDefaultRepository(tagSpringRepository);
    }
}
