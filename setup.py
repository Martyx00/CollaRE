from setuptools import setup, find_packages
from glob import glob

setup(
    name='collare',
    version='0.1',
    packages = ['collare'],
    package_data = {'collare' : ["icons/*"] },
    entry_points={
        'console_scripts': [
            'collare=collare.collare:main',
        ]
    },
    install_requires=[
        'PyQt5',
        'requests'
    ]
)

