-Demo Project Implementation

-From a high level point of view the demo consists of a list of posts, where each post has its own detail.

-Posts Screen
-
- A post has a title. To retrieve the posts, the following is used:

-* http://jsonplaceholder.typicode.com/posts

- When a post is tapped, the detail screen loads.

-Detail screen
-
- A post detail screen,:
-* Its author.
-* Its description (via the `body`).
-* Number of comments it has.

- Remaining information is retrieved from:

-* http://jsonplaceholder.typicode.com/users
-* http://jsonplaceholder.typicode.com/comments

-Requirements
-
-The focus of this demo is to use and explore:

-* Use Java and the latest Android SDK as a target platform.
-* The information (posts and post details) is available offline. It's assumed that if it's the first time you are accessing the app, and you are offline, you shouldn't see anything.

-Learning Outcome
-
- SOLID principles (https://en.wikipedia.org/wiki/SOLID_(object-oriented_design))
- Simple Design Principle.
- Code Consistency
- Readable, Scaleable and Compileable code.

-Libraries Used
-
-*Retrofit
-*Butterknife
-*Appcomapct v7
-*Pencil
