package com.wy.domain.jd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierTemplatePayMethodRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CashierTemplatePayMethodRelationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTemplateNoIsNull() {
            addCriterion("template_no is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNoIsNotNull() {
            addCriterion("template_no is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNoEqualTo(String value) {
            addCriterion("template_no =", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoNotEqualTo(String value) {
            addCriterion("template_no <>", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoGreaterThan(String value) {
            addCriterion("template_no >", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoGreaterThanOrEqualTo(String value) {
            addCriterion("template_no >=", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoLessThan(String value) {
            addCriterion("template_no <", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoLessThanOrEqualTo(String value) {
            addCriterion("template_no <=", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoLike(String value) {
            addCriterion("template_no like", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoNotLike(String value) {
            addCriterion("template_no not like", value, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoIn(List<String> values) {
            addCriterion("template_no in", values, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoNotIn(List<String> values) {
            addCriterion("template_no not in", values, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoBetween(String value1, String value2) {
            addCriterion("template_no between", value1, value2, "templateNo");
            return (Criteria) this;
        }

        public Criteria andTemplateNoNotBetween(String value1, String value2) {
            addCriterion("template_no not between", value1, value2, "templateNo");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1IsNull() {
            addCriterion("payment_method1 is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1IsNotNull() {
            addCriterion("payment_method1 is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1EqualTo(String value) {
            addCriterion("payment_method1 =", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1NotEqualTo(String value) {
            addCriterion("payment_method1 <>", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1GreaterThan(String value) {
            addCriterion("payment_method1 >", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1GreaterThanOrEqualTo(String value) {
            addCriterion("payment_method1 >=", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1LessThan(String value) {
            addCriterion("payment_method1 <", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1LessThanOrEqualTo(String value) {
            addCriterion("payment_method1 <=", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1Like(String value) {
            addCriterion("payment_method1 like", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1NotLike(String value) {
            addCriterion("payment_method1 not like", value, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1In(List<String> values) {
            addCriterion("payment_method1 in", values, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1NotIn(List<String> values) {
            addCriterion("payment_method1 not in", values, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1Between(String value1, String value2) {
            addCriterion("payment_method1 between", value1, value2, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod1NotBetween(String value1, String value2) {
            addCriterion("payment_method1 not between", value1, value2, "paymentMethod1");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2IsNull() {
            addCriterion("payment_method2 is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2IsNotNull() {
            addCriterion("payment_method2 is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2EqualTo(String value) {
            addCriterion("payment_method2 =", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2NotEqualTo(String value) {
            addCriterion("payment_method2 <>", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2GreaterThan(String value) {
            addCriterion("payment_method2 >", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2GreaterThanOrEqualTo(String value) {
            addCriterion("payment_method2 >=", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2LessThan(String value) {
            addCriterion("payment_method2 <", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2LessThanOrEqualTo(String value) {
            addCriterion("payment_method2 <=", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2Like(String value) {
            addCriterion("payment_method2 like", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2NotLike(String value) {
            addCriterion("payment_method2 not like", value, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2In(List<String> values) {
            addCriterion("payment_method2 in", values, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2NotIn(List<String> values) {
            addCriterion("payment_method2 not in", values, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2Between(String value1, String value2) {
            addCriterion("payment_method2 between", value1, value2, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod2NotBetween(String value1, String value2) {
            addCriterion("payment_method2 not between", value1, value2, "paymentMethod2");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3IsNull() {
            addCriterion("payment_method3 is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3IsNotNull() {
            addCriterion("payment_method3 is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3EqualTo(String value) {
            addCriterion("payment_method3 =", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3NotEqualTo(String value) {
            addCriterion("payment_method3 <>", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3GreaterThan(String value) {
            addCriterion("payment_method3 >", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3GreaterThanOrEqualTo(String value) {
            addCriterion("payment_method3 >=", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3LessThan(String value) {
            addCriterion("payment_method3 <", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3LessThanOrEqualTo(String value) {
            addCriterion("payment_method3 <=", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3Like(String value) {
            addCriterion("payment_method3 like", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3NotLike(String value) {
            addCriterion("payment_method3 not like", value, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3In(List<String> values) {
            addCriterion("payment_method3 in", values, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3NotIn(List<String> values) {
            addCriterion("payment_method3 not in", values, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3Between(String value1, String value2) {
            addCriterion("payment_method3 between", value1, value2, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPaymentMethod3NotBetween(String value1, String value2) {
            addCriterion("payment_method3 not between", value1, value2, "paymentMethod3");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusIsNull() {
            addCriterion("available_status is null");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusIsNotNull() {
            addCriterion("available_status is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusEqualTo(Byte value) {
            addCriterion("available_status =", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusNotEqualTo(Byte value) {
            addCriterion("available_status <>", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusGreaterThan(Byte value) {
            addCriterion("available_status >", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("available_status >=", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusLessThan(Byte value) {
            addCriterion("available_status <", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusLessThanOrEqualTo(Byte value) {
            addCriterion("available_status <=", value, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusIn(List<Byte> values) {
            addCriterion("available_status in", values, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusNotIn(List<Byte> values) {
            addCriterion("available_status not in", values, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusBetween(Byte value1, Byte value2) {
            addCriterion("available_status between", value1, value2, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andAvailableStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("available_status not between", value1, value2, "availableStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("created_date is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("created_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterion("created_date =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterion("created_date <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterion("created_date >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("created_date >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterion("created_date <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("created_date <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterion("created_date in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterion("created_date not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterion("created_date between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("created_date not between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIsNull() {
            addCriterion("modified_date is null");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIsNotNull() {
            addCriterion("modified_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedDateEqualTo(Date value) {
            addCriterion("modified_date =", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotEqualTo(Date value) {
            addCriterion("modified_date <>", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateGreaterThan(Date value) {
            addCriterion("modified_date >", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_date >=", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateLessThan(Date value) {
            addCriterion("modified_date <", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateLessThanOrEqualTo(Date value) {
            addCriterion("modified_date <=", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIn(List<Date> values) {
            addCriterion("modified_date in", values, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotIn(List<Date> values) {
            addCriterion("modified_date not in", values, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateBetween(Date value1, Date value2) {
            addCriterion("modified_date between", value1, value2, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotBetween(Date value1, Date value2) {
            addCriterion("modified_date not between", value1, value2, "modifiedDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}