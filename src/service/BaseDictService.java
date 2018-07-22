package service;

import domain.BaseDict;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:39
 **/
public interface BaseDictService {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
