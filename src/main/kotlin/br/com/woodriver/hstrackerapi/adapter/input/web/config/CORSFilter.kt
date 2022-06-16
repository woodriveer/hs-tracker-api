package br.com.woodriver.hstrackerapi.adapter.input.web.config

import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CORSFilter: Filter {
   override fun doFilter(request: ServletRequest, response: ServletResponse, filter: FilterChain) {
       val responseHttp = response as HttpServletResponse
       val requestHttp = request as HttpServletRequest

       responseHttp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200")
       responseHttp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
       responseHttp.setHeader("Access-Control-Max-Age", "3600")
       responseHttp.setHeader("Access-Control-Allow-Headers", "x-hstracker-authorization, x-requested-with, authorization, content-type")

       if ("OPTIONS".equals(requestHttp.method, ignoreCase = true)) {
           responseHttp.status = HttpServletResponse.SC_OK
       } else {
           filter.doFilter(request, response)
       }
   }
}