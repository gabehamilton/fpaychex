package fpaychex

class TimeOff {

	Date startTime
	Date endTime

	static belongsTo = User

    static constraints = {
    }
}
