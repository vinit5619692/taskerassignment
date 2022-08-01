define(['text!/../../json/config.json','/../../js/utils/service'],function(configFile,service){
    const config = JSON.parse(configFile);
    class ListviewService{
        constructor(){}
       async getAllTasks(endpoint){
         const api_url = service.buildEndpointURL(endpoint); 
         let data;
         try{            
              return fetch(api_url)
                .then(function(response) {
                // The response is a Response instance.
                // You parse the data into a useable format using `.json()`
                return response.json();
                }).then(function(data) {
                // `data` is the parsed version of the JSON returned from the above endpoint.
                return data.body;  // { "userId": 1, "id": 1, "title": "...", "body": "..." }
                });
         }catch(error){
            return null;
         }
         
         //return data;
        }

       
         async createUpdateTask(endpoint,data){
          const api_url = service.buildEndpointURL(endpoint); 
          try{            
               return fetch(api_url + '/post?' + new URLSearchParams({
                body: `${data.taskCode}~-~${data.taskDate}~-~${data.id}`
            }))
                 .then(function(response) {
                 // The response is a Response instance.
                 // You parse the data into a useable format using `.json()`
                 return response.json();
                 }).then(function(data) {
                 // `data` is the parsed version of the JSON returned from the above endpoint.
                 if(typeof data.body === 'undefined')
                    return data;  // { "userId": 1, "id": 1, "title": "...", "body": "..." }
                  else 
                      return data.body;
                 });
          }catch(error){
             return null;
          }
          
          //return data;
         }



         async deleteTask(endpoint,id){
          const api_url = service.buildEndpointURL(endpoint,id); 
          let data;
          try{            
               return fetch(api_url, {
                method: 'DELETE'})
                 .then(function(response) {
                 // The response is a Response instance.
                 // You parse the data into a useable format using `.json()`
                 return response.json();
                 }).then(function(data) {
                 // `data` is the parsed version of the JSON returned from the above endpoint.
                 return data.body;  // { "userId": 1, "id": 1, "title": "...", "body": "..." }
                 });
          }catch(error){
             return null;
          }
          
          //return data;
         }
    }
    return new ListviewService();
});