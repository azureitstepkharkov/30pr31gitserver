using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.NovaPoshtaRequest
{
    class TrackJson
    {
        public string apiKey { get; set; }
        public string modelName { get; set; }
        public string calledMethod { get; set; }
        public Document methodProperties { get; set; }
    }
}
