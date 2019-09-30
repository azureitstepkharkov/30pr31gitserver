using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.NovaPoshtaRequest
{
    class Document
    {
        public List<Documents> Documents { get; set; }
        public Document(List<Documents> doc)
        {
            Documents = doc;
        }
        public Document()
        {

        }
    }
}
