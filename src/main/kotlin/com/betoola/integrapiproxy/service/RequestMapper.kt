package com.betoola.integrapiproxy.service

import org.springframework.stereotype.Service
import com.betoola.integrapiproxy.dto.RestRequest
import com.betoola.integrapiproxy.dto.SoapRequest
import com.betoola.integrapiproxy.entity.MappingAction
import com.betoola.integrapiproxy.entity.MappingElement
import com.betoola.integrapiproxy.entity.RequestMappingRule

@Service
class RequestMapper {

    fun convert(restRequest: RestRequest): SoapRequest {
        return applyMappingRules(restRequest)
    }

    private fun applyMappingRules(restRequest: RestRequest): SoapRequest {
        val rules = findAllRestToSoapRequestMappingRulesById(123) // TODO: 12/10/19 pass in params
        val soapRequestPart = rules.map { applyRule(restRequest, it) }.toList()
        val reduce = soapRequestPart.reduce { acc, soapRequest -> acc.merge(soapRequest) } // TODO: 12/10/19 kinda
        return reduce

    }

    private fun applyRule(restRequest: RestRequest, rule: RequestMappingRule): SoapRequest {
        var soapRequestPart = listOf<SoapRequest>()
        if (rule.from == MappingElement.PARAM) {
            if (rule.to == MappingElement.PARAM) {
                soapRequestPart = restRequest.params.map { param -> applyAction(param, rule.orderedActions) }
            }
        }
        val reduce = soapRequestPart.reduce { acc, soapRequest -> acc.merge(soapRequest) } // TODO: 12/10/19 kinda
        return reduce
    }

    private fun applyAction(param: Map.Entry<String, Int?>, orderedActions: List<MappingAction>): SoapRequest {
        val soapRequest = SoapRequest()
        return if (orderedActions.first() == MappingAction.ASSERT_NOT_NULL) {
            val value = param.value ?: throw RuntimeException("Null assertion failed")
            soapRequest.params.put(param.key, value)
            soapRequest
        } else {
            throw RuntimeException("oops")
        }
    }

    private fun findAllRestToSoapRequestMappingRulesById(i: Int): List<RequestMappingRule> {
        val mappingRule = RequestMappingRule()
        mappingRule.from = MappingElement.PARAM
        mappingRule.to = MappingElement.PARAM
        mappingRule.orderedActions = listOf(MappingAction.ASSERT_NOT_NULL)
        return listOf(mappingRule)


    }
}