using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.NovaPoshtaRequest
{
    class TrackRequest
    {
        public string success { get; set; }
        public List<TrackDocData> data { get; set; }

        public List<Object> errors { get; set; }
        public List<Object> warnings { get; set; }
        public List<Object> info { get; set; }
        public List<Object> messageCodes { get; set; }
        public List<Object> errorCodes { get; set; }
        public List<Object> warningCodes { get; set; }
        public List<Object> infoCodes { get; set; }
    }
}
