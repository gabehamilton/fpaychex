package fpaychex

import org.springframework.dao.DataIntegrityViolationException

class TimeOffController {

	static scaffold = true
//    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
//
//    def index() {
//        redirect(action: "list", params: params)
//    }
//
//    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        [timeOffInstanceList: TimeOff.list(params), timeOffInstanceTotal: TimeOff.count()]
//    }
//
//    def create() {
//        [timeOffInstance: new TimeOff(params)]
//    }
//
//    def save() {
//        def timeOffInstance = new TimeOff(params)
//        if (!timeOffInstance.save(flush: true)) {
//            render(view: "create", model: [timeOffInstance: timeOffInstance])
//            return
//        }
//
//        flash.message = message(code: 'default.created.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), timeOffInstance.id])
//        redirect(action: "show", id: timeOffInstance.id)
//    }
//
//    def show(Long id) {
//        def timeOffInstance = TimeOff.get(id)
//        if (!timeOffInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [timeOffInstance: timeOffInstance]
//    }
//
//    def edit(Long id) {
//        def timeOffInstance = TimeOff.get(id)
//        if (!timeOffInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [timeOffInstance: timeOffInstance]
//    }
//
//    def update(Long id, Long version) {
//        def timeOffInstance = TimeOff.get(id)
//        if (!timeOffInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "list")
//            return
//        }
//
//        if (version != null) {
//            if (timeOffInstance.version > version) {
//                timeOffInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
//                          [message(code: 'timeOff.label', default: 'TimeOff')] as Object[],
//                          "Another user has updated this TimeOff while you were editing")
//                render(view: "edit", model: [timeOffInstance: timeOffInstance])
//                return
//            }
//        }
//
//        timeOffInstance.properties = params
//
//        if (!timeOffInstance.save(flush: true)) {
//            render(view: "edit", model: [timeOffInstance: timeOffInstance])
//            return
//        }
//
//        flash.message = message(code: 'default.updated.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), timeOffInstance.id])
//        redirect(action: "show", id: timeOffInstance.id)
//    }
//
//    def delete(Long id) {
//        def timeOffInstance = TimeOff.get(id)
//        if (!timeOffInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "list")
//            return
//        }
//
//        try {
//            timeOffInstance.delete(flush: true)
//            flash.message = message(code: 'default.deleted.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "list")
//        }
//        catch (DataIntegrityViolationException e) {
//            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'timeOff.label', default: 'TimeOff'), id])
//            redirect(action: "show", id: id)
//        }
//    }
}
