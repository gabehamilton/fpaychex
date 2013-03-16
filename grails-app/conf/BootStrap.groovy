import fpaychex.User

class BootStrap {

    def init = { servletContext ->

		User user = new User(name: 'Mikkel Garcia').save()
		User user1 = new User(name: 'Gabe Hamilton').save()


    }
    def destroy = {
    }
}
