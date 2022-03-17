package com.kjwon.myblog.admin.repository;

import com.kjwon.myblog.admin.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findAll();
    List<Banner> findByIsDelete(boolean isDelete);

    @Transactional
    @Modifying
    @Query("update Banner b set b.isDelete = true WHERE b.id =:id")
    void setCustomerName(@Param("id") Long id);

    @Modifying
    @Query("select b from Banner b  WHERE b.isDelete = false AND b.isDisplay = true order by  b.bannerDisplayNo asc")
    List<Banner> selectBanner();
//    void updateIsDeleteById(boolean isDelete, long id);
}
