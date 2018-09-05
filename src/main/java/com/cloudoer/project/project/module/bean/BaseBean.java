package com.cloudoer.project.project.module.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Id;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/5
 */
@Data
class BaseBean {

    /**
     * 如果主键是id，则继承BaseBean即可。
     * 如果是其他名字，例如userId，则不继承BaseBean，
     * 在子类中单独声明userId字段，并加上@Id注解。
     */
    @Id
    private Long id;

    /**
     * 乐观锁
     */
    @Version
    private Long version;
}
