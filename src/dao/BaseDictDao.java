package dao;

import domain.BaseDict;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:40
 **/
public interface BaseDictDao extends BaseDao<BaseDict> {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
