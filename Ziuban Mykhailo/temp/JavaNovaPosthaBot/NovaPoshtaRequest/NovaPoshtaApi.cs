using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.NovaPoshtaRequest
{
    class NovaPoshtaApi
    {
        public static TrackRequest SendRequest(string doc, string phone)
        {
            TrackJson track = new TrackJson();
            track.apiKey = "6e6630e3e239ed75e893776a4ddd25d9";
            track.modelName = "TrackingDocument";
            track.calledMethod = "getStatusDocuments";
            List<Documents> docs = new List<Documents> { new Documents { DocumentNumber = doc, Phone = phone } };
            track.methodProperties = new Document(docs);
            string a = JsonConvert.SerializeObject(track);

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:6740");
                StringContent content = new StringContent(JsonConvert.SerializeObject(track));
                HttpResponseMessage result = client.PostAsync("https://api.novaposhta.ua/v2.0/json/", content).Result;
                return  JsonConvert.DeserializeObject<TrackRequest>(result.Content.ReadAsStringAsync().Result);
            }
        }
    }
}
