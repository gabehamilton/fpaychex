class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "calendar", view:"/index")
		"500"(view:'/error')
	}
}
