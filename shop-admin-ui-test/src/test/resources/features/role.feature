Feature: Role

  Scenario Outline: login and insert new Role
#   login
    Given I open web browser for Roles page
    When I navigate to Roles Page
    Then Is open Login page for Role page
    When I insert username as "<username>" and password as "<password>" for Role page
    And I click on login button for Role page
#   insert new role
    Then Is open role page
    When I click on Add role button
    Then is open page  Add role
    When I insert new role title "<newRole>"
    And I click on Submit new Role Button
    Then Is open role page
    Then Is the list Roles include "<newRole>"



    Examples:
      | username | password | newRole   |
      | alex     | 1        | ROLE_TEST |