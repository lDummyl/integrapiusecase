package com.betoola.integrapiproxy.entity

class RequestMappingRule {

    var from = MappingElement.PARAM
    var to = MappingElement.PARAM
    var orderedActions: List<MappingAction> = listOf(MappingAction.ASSERT_NOT_NULL)

}

enum class MappingAction {
    CAST_STRING_TO_INT,
    TRIM,
    ASSERT_NOT_NULL

}

enum class MappingElement {
    PARAM,
    BODY

}
