package org.breizhcamp.dorikell.infrastructure.elasticsearch

import org.breizhcamp.dorikell.infrastructure.elasticsearch.model.PersonDocument
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.*

interface PersonRepo: ElasticsearchRepository<PersonDocument, UUID> {

    @Query("""
    {
        "bool": {
            "must": [
                {
                    "match": { "event.id": "?1" }
                },
                {
                    "bool": {
			            "should": [
				            {
					            "multi_match": {
						            "query": "?0",
    						        "fields": [	"lastname", "firstname", "company" ],
        						    "fuzziness": "AUTO"
	        				    }
		        		    },
			        	    {
				        	    "prefix": {
				    	    	    "firstname": "?0"
					            }
    				        },
	    			        {
		    			        "prefix": {
			    			        "lastname": "?0"
				    	        }
				            },
				            {
    					        "prefix": {
	    					        "company": "?0"
		    			        }
			    	        }
			            ]
                    }
                }
            ]
		}
    }""")
    fun findAllByQueryAndEvent_Id(query: String, eventId: Int): List<PersonDocument>

    fun findByBarcodeAndEvent_Id(barcode: String, eventId: Int): Optional<PersonDocument>

    fun findByCodeAndEvent_Id(code: Int, eventId: Int): Optional<PersonDocument>

}