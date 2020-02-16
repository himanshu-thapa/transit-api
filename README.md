# Transit Guide (With Vehicle Predictions)
<h3>Technical track</h3>

I've selected back-end track with some focus towards front-end.

<h3>Technologies used</h3>

<p>Server-side:</p>

<li> Spring Boot</li>
<li> RestTemplate</li>
<li> Maven</li>
<br>
<p>Client-side:</p>

<li> Twitter Bootstrap</li>
<li> JQuery/AJAX</li>
<li> Leaflet.js Map</li>

<h3>Architecture justification</h3>

<p>
Transit guide app consumes external rest API from Nextbus.com and depends on their datasources. It is said that-"completely relying
on external data sources for any app is not a good approach". But before actually writing this piece of code, i played with NextBus
API and didn't find any glitches so i decided not to dump data into my database for now.

The basic logic behind the app is, we need to list down all the transportation agencies as page loads. We show  all the available
routes for any selected agency. The markers will be plotted in map for agency's route defining all the stops in the way. Finally if
any STOP is selcted from dropdown of that particular route and agency,one will see all the vehicles of that agency with departure time
or arrival time. Both of these times are predicted times.
</p>

<h3>Architecture overview</h3>

<h4>Client side</h4>

Front-end built using twitter bootstrap, sends requests to fetch all agencies,their routes,all stops and predictions using AJAX. 
Leaflet js is used for showing routes and their stops on an interactive map.

<h4>Server side</h4>

Since we are not dumping any data into database, we lack database related components in this app. Every request from client side
eventually makes a request to Nextbus datasource. For making requests and getting responses, i used Spring's RestTemplate since
it is easy to make HTTP Rest calls. I've created POJO's for object mapping and providing responses to Transit guide app's REST calls.

I've created a Generic response class which is returned in every api response. Since this Generic class needs to instantiate in every api call,
i made it singleton in order to prevent multiple instantitaions.

Maven is used for building project and managing dependencies.

<h3> API's</h3>

<h4>Agencies</h4>

GET /api/agency/all

<h4>Agency Routes</h4>

GET /api/agency/routes

Request Parameters:
<li> agencyTag (String)</li>

<h4>Route Details</h4>

GET /api/agency/route-details

Request Parameters:
<li> agencyTag (String)</li>
<li> routeTag (String)</li>

<h4> STOP Prediction </h4>

GET /api/agency/stop/predictions

Request Parameters:
<li> agencyTag (String)</li>
<li> routeTag (String)</li>
<li> stopTag (String)</li>

<h3> Future improvements </h3>

<li>Implement database for external data dump</li>
<li>Implement multiple predictions</li>
<li>Real time vehicle track system </li>
