# Quarkus + Selenium + Gherkin (Cucumber)

## Usage for development

- start dev server:

    ```bash
    mvn compile quarkus:dev
    ```

- Goto Web <http://localhost:8080>
- Goto REST resource <http://localhost:8080/resources/surveys>
- Goto OpenAPI <http://localhost:8080/openapi>
- Goto Swagger UI <http://localhost:8080/swagger-ui>

## Run Selenium locally
First you need to prepare your system. Download and unpack the driver for Firefox or Chrome. Put them somewhere on your `PATH`.
- Firefox -> install <https://github.com/mozilla/geckodriver/releases>
- Chrome ->  install <https://sites.google.com/a/chromium.org/chromedriver/downloads>

You need to have a running server and then separately start the test.

    mvn compile quarkus:dev

In a separate window, run the tests

    mvn test

You may want to experiment with (with `-D` start option, in `microprofile-config.properties`, or environment variable):
- `usewebdriver` to set to "chome", "firefox" or "remote"
- `webdriverRemoteUrl` (default: http://localhost:4444/wd/hub)
- `homepageBaseUrl` (default: http://localhost:8080/)

## Selenium Test Grid (RemoteWebDriver)
Start your personal selenium grid locally with:

	docker run --rm -p 4444:4444 --name selenium-hub selenium/hub
	docker run --rm --link selenium-hub:hub selenium/node-chrome
	docker run --rm --link selenium-hub:hub selenium/node-firefox