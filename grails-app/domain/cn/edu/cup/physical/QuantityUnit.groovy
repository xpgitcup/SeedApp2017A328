package cn.edu.cup.physical

/*
 * 物理量单位
 * */
class QuantityUnit {

    //字段定义
    String unitName;        //名称
    String description;     //描述
    String label;           //符号--英文符号
    String name;            //英文名称
    Double factorA;
    Double factorB;

    UnitSystem unitSystem;                  //所属单位制

    static belongsTo = [physicalQuantity: PhysicalQuantity]

    static constraints = {
        unitName(unique: true);
        name(nullable: true)
        label()
        description();
        factorA();
        factorB();
        physicalQuantity();
        unitSystem();
    }

    //定义排序
    static mapping = {
        sort('physicalQuantity')
        sort('factorA')
    }

    String toString() {
        return "${label}/${description}"
    }

    boolean isISOUnit() {
        if (physicalQuantity.isoUnit) {
            return physicalQuantity.isoUnit.unitName == unitName
        } else {
            return false
        }
    }

    def toIsoUnit(Double value) {
        return value * factorA + factorB
    }

    def fromIsoUnit(Double value) {
        return (value - factorB) / factorA
    }

}
