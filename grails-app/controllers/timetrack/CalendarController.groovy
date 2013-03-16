package fpaychex

class CalendarController {

	def index() {
		[users: User.list(), timeOffs: TimeOff.list()]
	}
}
