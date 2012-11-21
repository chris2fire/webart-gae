WebArt sample for Google App Engine
====================================

WebArt site based on Google App Engine, Bootstrap, jQuery, audio.js, html5shiv.

This code has been used to produce a Art Website at http://revue5.appendices.fr.
At each visit, the website generates a unique movie composed by drawings, pictures and musics (art contributions).
Displaying order and time is randomly computed for each visitor, the more art contributions are, the more movie is unique.

Feel free to fork it, change it, share it and deploy it as you want.

Add your Art contributions can be achieved by "add.html" page.
Currently, there is not upload module. Art contributions can be directly available on Internet (complete url in "link/data" field) or can be deployed on GAE static dir (relative url img/something.jpg in "link/data" field).
You can protect or limit art contributor by setting an authToken in the auth.properties.

Enjoy!!

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0