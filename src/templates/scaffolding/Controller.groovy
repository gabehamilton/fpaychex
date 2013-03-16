<%=packageName ? "package ${packageName}\n\n" : ''%>

import org.springframework.dao.DataIntegrityViolationException
import javax.servlet.http.HttpServletResponse
import grails.converters.JSON
import grails.converters.XML
import org.codehaus.groovy.grails.web.json.JSONException

class ${className}Controller {

    def filterPaneService

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
	private static final String PROMO_FILTER_PROPS = "name,status,startDate"
	private static final String PRODUCT_FILTER_PROPS = "name,sku,price"

    def filter = {
        if(!params.max) params.max = 10
		params.sort = params.sort ?: 'id'
		params.order = params.order ?: 'desc'
        
		List<${className}> ${propertyName}List = ${className}.list(params)
		Integer ${propertyName}Total = ${className}.count()

        render( view:'list', 
            model: [filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
            params:params,
            ${propertyName}List: filterPaneService.filter(params, ${className}), 
            ${propertyName}Total: filterPaneService.count(params, ${className}),
            filterProps: determineFilterProps()])
    }

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: 'id'
		params.order = params.order ?: 'desc'
        //println ' list parrrrrrrrrrrrams: ' + params
		List<${className}> ${propertyName}List = ${className}.list(params)
		Integer ${propertyName}Total = ${className}.count()

		render ${propertyName}List as JSON
//		request.withFormat {
//			html {
//				[${propertyName}List: ${propertyName}List, ${propertyName}Total: ${propertyName}Total, filterProps: determineFilterProps()]
//			}
//			json {
//				render ${propertyName}List as JSON
//			}
//			xml {
//				render ${propertyName}List as XML
//			}
//		}
    }

    def create = {
        def ${propertyName} = new ${className}()
        ${propertyName}.properties = params
        return [${propertyName}: ${propertyName}]
    }

    def save = {
		request.withFormat{
			html {}
			json {
				def text = request?.inputStream?.text
				if(text) {
					try {
						JSON.parse(text).entrySet().each {
							params.put it.key, it.value
						  }
					} catch (JSONException ignored) {}
				}
			}
			xml {}
		}
        def ${propertyName} = new ${className}(params)
        if (${propertyName}.save(flush: true)) {
            flash.message = "\${message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])}"
			request.withFormat{
				html {
					redirect(action: "show", id: ${propertyName}.id)
				}
				json {
					response.setStatus(HttpServletResponse.SC_CREATED)
					render ${propertyName} as JSON
				}
				xml {
					response.setStatus(HttpServletResponse.SC_CREATED)
					render ${propertyName} as XML
				}
			}
        }
        else {
			request.withFormat {
				html {
					render(view: "create", model: [${propertyName}: ${propertyName}])
				}
				json {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST)
					render ${propertyName}.errors as JSON
				}
				xml {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST)
					render ${propertyName}.errors as XML
				}
			}
        }
    }

    def show = {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
			String message = "\${message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])}"
			request.withFormat {
				html {
					flash.message = message
					redirect(action: "list")
				}
				json {
					response.sendError(HttpServletResponse.SC_NOT_FOUND)
					render "{error: '\${message}'}"
				}
				xml {
					response.sendError(HttpServletResponse.SC_NOT_FOUND)
					render "<error>\${message}</error>"
				}
			}
        }
        else {
			request.withFormat {
				html {
					[${propertyName}: ${propertyName}]
				}
				json {
					def o = ${propertyName}.get(params.id)
					render o as JSON
				}
				xml {
					def o = ${propertyName}.get(params.id)
					render o as XML
				}
			}
        }
    }

    def edit = {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
            flash.message = "\${message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [${propertyName}: ${propertyName}]
        }
    }

	def updateValidationFailedDestination = {${propertyName} ->
		request.withFormat {
			html {
				render(view: "edit", model: [${propertyName}: ${propertyName}])
			}
			json {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST)
				render ${propertyName}.errors as JSON
			}
			xml {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST)
				render ${propertyName}.errors as XML
			}
		}
	}
		
	// where to go on successful update
	def updateActionDestination = {${propertyName} ->
		request.withFormat{
			html {
				redirect(action: "show", id: ${propertyName}.id)
			}
			json {
				render ${propertyName} as JSON
			}
			xml {
				render ${propertyName} as XML
			}
		}
	}

	// where to go on successful delete
	def deleteActionDestination = {
		redirect(action: "list")
	}

    def update = {
		request.withFormat{
			html {}
			json {
				def text = request?.inputStream?.text
				if(text) {
					try {
						JSON.parse(text).entrySet().each {
							params.put it.key, it.value
						  }
					} catch (JSONException ignored) {}
				}
			}
			xml {}
		}
        def ${propertyName} = ${className}.get(params.id)
        if (${propertyName}) {
            if (params.version) {
                def version = params.version.toLong()
                if (${propertyName}.version > version) {
                    <% def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className) %>
					String message = "Another user has updated this ${className} while you were editing"
					request.withFormat {
						html {
							${propertyName}.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[], message)
							render(view: "edit", model: [${propertyName}: ${propertyName}])
						}
						json {
							response.sendError(HttpServletResponse.SC_CONFLICT)
							render "{error: 'Another user has updated this ${className} while you were editing'}"
						}
						xml {
							response.sendError(HttpServletResponse.SC_CONFLICT)
							render "<error>Another user has updated this ${className} while you were editing</error>"
						}
					}
                    return
                }
            }
            ${propertyName}.properties = params
            if (!${propertyName}.hasErrors() && ${propertyName}.save(flush: true)) {
                flash.message = "\${message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.toString() + ', '])}"
				updateActionDestination(${propertyName})
            }
            else {
				if(!${propertyName}.hasErrors()) {
					${propertyName}.errors.reject('default.not.updated.message',
					   [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}?.id] as Object[],
					   '[${className} could not be saved]')
				}
				updateValidationFailedDestination(${propertyName})
            }
        }
        else {
			String message = "\${message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])}"
			request.withFormat {
				html {
					flash.message = message
					redirect(action: "list")
				}
				json {
					response.sendError(HttpServletResponse.SC_NOT_FOUND)
					render "{error: '\${message}'}"
				}
				xml {
					response.sendError(HttpServletResponse.SC_NOT_FOUND)
					render "<error>\${message}</error>"
				}
			}
        }
		return ${propertyName}
    }

    def delete = {
        def ${propertyName} = ${className}.get(params.id)
        if (${propertyName}) {
			String displayName = (${propertyName}.toString() ?: '') + ', ' + ${propertyName}.id
            try {
				${propertyName}.withTransaction {
	                ${propertyName}.delete()
				}
                flash.message = "\${message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), displayName])}"
				deleteActionDestination()
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "\${message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), displayName])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "\${message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])}"
            redirect(action: "list")
        }
    }

    private String determineFilterProps() {
        def filterProps

        if("${className}".contains("Promotion")) {
            filterProps = PROMO_FILTER_PROPS
        } else if("${className}".contains("Product")) {
            filterProps = PRODUCT_FILTER_PROPS
        } else {
            filterProps = "name" // most domain classes should have a name to filter by
        }
    }
}
