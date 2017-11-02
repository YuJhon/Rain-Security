package com.jhon.rain.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <p>功能描述</br> 基础的Repository类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRepository
 * @date 2017/11/2 19:43
 */
@NoRepositoryBean
public interface RainRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
