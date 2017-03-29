package cn.edu.cup.dictionary

class DataItem {

    DataKey labelKey
    String  value

    static belongsTo = [parentItem: DataItem]

    static hasMany = [subItems: DataItem]

    static mapping = {
        sort "labelKey"
    }

    static constraints = {
        labelKey()
        value(nullable: true)
        parentItem(nullable: true)
    }

    String toString() {
        return "${labelKey}=${value}"
    }

}
