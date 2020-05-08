# testFlowAMZ
AMZ add to cart flow.


Please add test apk to app folder!!!  

Steps covered :
 1. Open Amazon application. 
 2. Skip login (no account available for testing).    
 3. Verify homepage is reached. 
 4. Run search query with given searchTerm. 
 5. Make sure Delivery is set to US (default was set to current location and could lead to no results or different experience) After setting the Delivery the home page is displayed so the search must be performed again. 
 6. Gather list of results and clear unsuitable items from the list , select not first or last item , click it and confirm the details page includes the same title.    
 7. Further verifications are impossible without a proper test account. 
