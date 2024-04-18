$(document).ready(function() {
    // Event listener for the search button click
    $('#search-btn').click(function() {
        // Retrieve values
        var description = $('#search-box').val();
        var explicit = $('#explicit').is(':checked');
        var popular = $('#popular').is(':checked');

        // Object to hold data
        var user_preferences = {
            description: description,
            explicit: explicit,
            popular: popular
        };

        // Send the data to the controller
        loadPlaylist(user_preferences);
    });
});

function loadPlaylist(user_preferences) {
    $.ajax({
        url: "/playlist",
        type: "POST",
        contentType: "application/json",  // Setting the content type as JSON
        data: JSON.stringify(user_preferences),  // Stringify data
        success: function(response) {
            console.log(response);
        },
        error: function(xhr, status, error) {
            console.error("Error: ", error);
        }
    });
}
