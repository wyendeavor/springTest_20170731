package com.wy.dao.jd;

import com.wy.domain.jd.CashierTemplatePayMethodRelation;
import com.wy.domain.jd.CashierTemplatePayMethodRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashierTemplatePayMethodRelationMapper {
    long countByExample(CashierTemplatePayMethodRelationExample example);

    int deleteByExample(CashierTemplatePayMethodRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CashierTemplatePayMethodRelation record);

    int insertSelective(CashierTemplatePayMethodRelation record);

    List<CashierTemplatePayMethodRelation> selectByExample(CashierTemplatePayMethodRelationExample example);

    CashierTemplatePayMethodRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CashierTemplatePayMethodRelation record, @Param("example") CashierTemplatePayMethodRelationExample example);

    int updateByExample(@Param("record") CashierTemplatePayMethodRelation record, @Param("example") CashierTemplatePayMethodRelationExample example);

    int updateByPrimaryKeySelective(CashierTemplatePayMethodRelation record);

    int updateByPrimaryKey(CashierTemplatePayMethodRelation record);
}