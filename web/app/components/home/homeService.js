angular.module('appHomeService', []).factory('homeContent', function () {
    return {
        getHomeComponentOneContent: function () {
            var content = [
                {
                    imgUrl: 'https://3.bp.blogspot.com/-c-OpMD_qF4Y/U_xJfbIqUzI/AAAAAAAAHgA/rfDuMp2IfCQ/s1600/github_icon.png',
                    subheading: 'Search GitHub Repositories',
                    text: 'Please type a topic you would like to search for:'
                }/*, {
                    subheading: 'Results',
                    text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor ' +
                        'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ' +
                        'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ' +
                        'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ' +
                        'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit ' +
                        'anim id est laborum.'
                }*/
            ];
            return content;
        }
    };
});