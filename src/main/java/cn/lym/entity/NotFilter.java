package cn.lym.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : liuymz
 * @date : Created in 2018/3/9 15:15
 */
@Entity
@Data
public class NotFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String path;

}
