package com.fastcompaus.mycontact.reporitory;

import com.fastcompaus.mycontact.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository     // JpaRepository를 extends 하는 경우,
                // 자동으로 Repository 빈으로 등록되기 때문에 따로 선언해주지 않아도 된다.
public interface BlockRepository extends JpaRepository<Block, Long> {
}
