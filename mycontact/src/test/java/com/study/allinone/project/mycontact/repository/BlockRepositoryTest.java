package com.study.allinone.project.mycontact.repository;

import com.study.allinone.project.mycontact.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block();
        block.setName("martin");
        block.setReason("친하지 않아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);


        List<Block> blocks = blockRepository.findAll();

        assertThat(blocks.size()).isEqualTo(3);
        assertThat(blocks.get(0).getName()).isEqualTo("jason");
        assertThat(blocks.get(1).getName()).isEqualTo("Yuri");
        assertThat(blocks.get(2).getName()).isEqualTo("martin");
    }

}