angular.module('myAppHomeService', [])

    .factory('homeContent', function () {
        return {
            getHeader: function () {
                return "Welcome";
            },
            getSubheader: function () {
                return "To Angelo's Awesome GitHub Crawler";
            },
            getContent: function () {
                var content = [
                    {
                        imgUrl: 'https://3.bp.blogspot.com/-c-OpMD_qF4Y/U_xJfbIqUzI/AAAAAAAAHgA/rfDuMp2IfCQ/s1600/github_icon.png',
                        heading: 'Some Heading',
                        subheading: 'Quiet Heading',
                        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor ' +
                            'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ' +
                            'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ' +
                            'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ' +
                            'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit ' +
                            'anim id est laborum.'
                    }, {
                        imgUrl: 'http://aashni.me/images/somacro/border/stackoverflow.png',
                        heading: 'Some Heading',
                        subheading: 'Quiet Heading',
                        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor ' +
                            'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ' +
                            'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ' +
                            'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ' +
                            'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit ' +
                            'anim id est laborum.'
                    }, {
                        imgUrl: 'http://aashni.me/images/somacro/border/stackoverflow.png',
                        heading: 'Some Heading',
                        subheading: 'Quiet Heading',
                        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor ' +
                            'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ' +
                            'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ' +
                            'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ' +
                            'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit ' +
                            'anim id est laborum.'
                    }
                ];
                return content;
            }
        };
    });