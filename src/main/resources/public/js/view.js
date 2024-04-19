$(document).ready(function() {
    var pathname = window.location.pathname;

    // Check if we are on the index page
    if (pathname === '/' || pathname === '/index.html') {
        $('#search-btn').click(function() {
            var description = $('#search-box').val();
            var explicit = $('#explicit').is(':checked');
            var popular = $('#popular').is(':checked');

            var queryParams = $.param({
                description: description,
                explicit: explicit,
                popular: popular
            });

            // Redirect to the search results page with query parameters
            window.location.href = `search_results.html?${queryParams}`;
        });
    }

    // Check if we are on the search results page
    if (pathname.includes('search_results.html')) {
        // Function to extract query parameters and load the playlist
        function loadPlaylistFromParams() {
            var queryParams = new URLSearchParams(window.location.search);
            var user_preferences = {
                description: queryParams.get('description'),
                explicit: queryParams.get('explicit') === 'true',
                popular: queryParams.get('popular') === 'true'
            };

            loadPlaylist(user_preferences);
        }

        // Function to load the playlist data
        function loadPlaylist(user_preferences) {
            $.ajax({
                url: "/search",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(user_preferences),
                success: function(response) {
                    $('#results-table tbody').empty();
                    response.forEach(track => {
                        $('#results-table tbody').append(`
                            <tr>
                                <td><a href="${formatSpotifyLink(track.trackHref)}" target="_blank">${track.name}</a></td>
                                <td>${track.artist}</td>
                                <td>${track.formattedDuration}</td>
                                <td>${track.popularity}</td>
                                <td>${track.explicit}</td>
                                <td>${track.danceability}</td>
                                <td>${track.energy}</td>
                                <td>${track.key}</td>
                                <td>${track.loudness}</td>
                                <td>${track.mode}</td>
                                <td>${track.speechiness}</td>
                                <td>${track.acousticness}</td>
                                <td>${track.instrumentalness}</td>
                                <td>${track.liveness}</td>
                                <td>${track.valence}</td>
                                <td>${track.tempo}</td>
                                <td>${track.type}</td>
                                <td>${track.spotifyId}</td>
                                <td>${track.uri}</td>
                                <td>${track.timeSignature}</td>
                            </tr>
                        `);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Error: ", error);
                }
            });
        }

        function formatSpotifyLink(apiHref) {
            const match = apiHref.match(/tracks\/(.+)/);
            if (match) {
                return `https://open.spotify.com/track/${match[1]}`;
            }
            return '#'; // Return a safe fallback URL
        }

        // Load the playlist after the page is fully loaded
        loadPlaylistFromParams();
    }
});
