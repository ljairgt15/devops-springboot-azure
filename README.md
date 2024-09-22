# devops-springboot-azure
In order to complete devops exercise for TCS
---
evOps Springboot TCS Project
Project Description
This project implements a Spring Boot application with an automated CI/CD pipeline using Azure DevOps for building, packaging, and deploying a Docker image on an Azure Kubernetes Service (AKS) cluster. The goal is to automate the lifecycle of an application, from build to deployment, in a containerized environment using AKS.

Key Features:
Spring Boot: The application is developed in Spring Boot and exposes secure API endpoints using a JWT token.
Docker: The application is packaged as a Docker image.
Azure DevOps: A CI/CD pipeline automates the build and deployment process.
Azure Kubernetes Service (AKS): The application is deployed on an AKS cluster.
Azure Container Registry (ACR): Docker images are stored and managed in ACR.
Project Setup
1. Generate JWT Token
Within the Spring Boot project, JWT-based authentication has been implemented. You can generate a token to test the API. This token must be sent in the Authorization headers to access protected endpoints.

2. AKS (Azure Kubernetes Service)
The project includes the configuration to deploy the application Docker image to AKS.

Challenges Encountered
Unfortunately, I was unable to provide the final URL to access the deployed service due to limitations with my personal cloud computing account, specifically related to vCPU quotas in my Azure subscription. Because of these restrictions, I could not fully complete the AKS deployment.

I typically work with enterprise tenants that have sufficient quotas to handle such deployments.

If you would like to try the project yourself, please ensure you have an active Azure subscription with adequate resources to deploy to AKS. The pipeline is already configured, and it should work once the cluster and service are properly set up.

I apologize for any inconvenience caused by these infrastructure limitations. I hope the rest of the project is helpful and demonstrates the necessary technical skills.
